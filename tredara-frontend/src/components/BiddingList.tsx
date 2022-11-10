import iBidding from "interfaces/iBidding";
import Table from "react-bootstrap/Table";
import InputGroup from "react-bootstrap/InputGroup";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useEffect, useState, FormEvent } from "react";
import iAuctionItem from "interfaces/iAuctionItem";
import eStatus from "interfaces/eStatus";
import { HandThumbsUpFill, ArrowRepeat } from "react-bootstrap-icons";
import { useUser } from "state/UserContext";
import moment from "moment";
import StatusLoading from "./StatusLoading";
import StatusError from "./StatusError";
import { Row, Col } from "react-bootstrap";

interface iProps {
  data: iAuctionItem;
}
export default function BiddingList({ data }: iProps) {
  const [status, setStatus] = useState(eStatus.LOADING);
  const [bids, setBids] = useState<iBidding[]>([]);
  const { user } = useUser();
  const [bidPrice, setBidPrice] = useState<number>(data.original_price);

  const isUserEligible = user?.email === data.user_email;

  useEffect(() => {
    fetch(`/auctionitems/getbiddings/${data.id}`)
      .then((response) => response.json())
      .then((data) => onSuccess(data))
      .catch((error) => onFailure(error));
  }, [status]);

  const formatTime = (time: any) => {
    return moment(time).format("DD-MM-YYYY hh:mm:ss");
  };

  const getHighestBid = () => {
    let allBids = bids.map((bid) => bid.bidding_price);
    let highestBid = Math.max(...allBids);
    return highestBid;
  };

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    // CONDITION: created user (owner) shouldn't allow to bid
    // CONDITION: BID value should be higher than the latest bid.

    if (bidPrice <= getHighestBid()) {
      alert(`Please enter a bid value more than ${getHighestBid()}`);
      event.preventDefault();
      event.stopPropagation();
    } else if (bidPrice <= data.original_price) {
      alert(`Please enter a bid value more than ${data.original_price}`);
      event.preventDefault();
      event.stopPropagation();
    } else {
      onSubmit(event);
    }
  }
  async function onSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const newBidd = {
      bidding_price: bidPrice,
      bidder_email: user?.email,
      bidder: user?.name,
      auction_item: data.id,
      created_on: formatTime(Date.now()),
    };
    fetch(`/auctionitems/updatebidding`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      body: JSON.stringify(newBidd),
    })
      .then(() => setStatus(eStatus.LOADING))
      .catch((error) => onFailure(error));
  }

  function onSuccess(data: iBidding[]) {
    setBids(data);
    setStatus(eStatus.READY);
  }

  function onFailure(error: string) {
    console.error(error);
    setStatus(eStatus.ERROR);
  }

  const Rows = bids.map((bid) => (
    <tbody>
      <tr>
        <td>{bid.id}</td>
        <td>{bid.bidder}</td>
        <td>{bid.created_on}</td>
        <td>{bid.bidding_price}</td>
      </tr>
    </tbody>
  ));

  // Safeguards
  if (status === eStatus.LOADING) return <StatusLoading />;
  if (status === eStatus.ERROR) return <StatusError />;

  return (
    <Row>
      <h2>Bidding History</h2>
      <Form onSubmit={handleSubmit}>
        <InputGroup className="mr-sm-2">
          <Form.Control
            placeholder="Enter bid value"
            type="number"
            onChange={(event) => setBidPrice(Number(event.target.value))}
          />
          <Button
            type="submit"
            variant="primary"
            id="button-addon2"
            disabled={isUserEligible}
          >
            <HandThumbsUpFill />
          </Button>
          <Button
            variant="primary"
            id="button-addon2"
            onClick={() => setStatus(eStatus.LOADING)}
          >
            <ArrowRepeat />
          </Button>
        </InputGroup>

        <Table striped bordered hover style={{ marginTop: "1rem" }}>
          <thead>
            <tr>
              <th>#</th>
              <th>Bidder</th>
              <th>Created On</th>
              <th>Bidding Price</th>
            </tr>
          </thead>
          {Rows}
        </Table>
      </Form>
    </Row>
  );
}
