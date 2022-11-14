import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import eStatus from "interfaces/eStatus";
import iAuctionItem from "interfaces/iAuctionItem";
import StatusError from "components/StatusError";
import StatusLoading from "components/StatusLoading";
import Placeholder from "assets/images/placeholders/card.png";
import BiddingList from "components/BiddingList";
import { Container, Row, Col, Button, Form } from "react-bootstrap";
import { useUser } from "state/UserContext";
import eUserType from "interfaces/eUserType";

export default function ItemDetail() {
  const [status, setStatus] = useState(eStatus.LOADING);
  const [data, setData] = useState({} as iAuctionItem);
  const { id }: any = useParams();
  const { user } = useUser();

  const isUserOwner = user?.email === data.user_email;
  const isAdmin = user?.type === eUserType.ADMIN;

  useEffect(() => {
    fetch(`/auctionitems/details/${id}`)
      .then((response) => response.json())
      .then((data) => onSuccess(data))
      .catch((error) => onFailure(error));
  }, [id]);

  function onSuccess(data: iAuctionItem) {
    setData(data);
    setStatus(eStatus.READY);
  }

  function onFailure(error: string) {
    console.error(error);
    setStatus(eStatus.ERROR);
  }

  // Safeguards
  if (status === eStatus.LOADING) return <StatusLoading />;
  if (status === eStatus.ERROR) return <StatusError />;

  return (
    <Container id="details" className="container-fluid detail">
      <Row>
        <Col>
          <img className="img-fluid" src={data.image} 
          onError={(event) => (event.currentTarget.src = Placeholder)} />
          <div className="mt-3">
            <h4 className="text-dark">{data.title}</h4>
            <h6 className="text-secondary">{data.description}</h6>
            <hr />
            <div className="d-flex justify-content-between mt-3">
              <h6 className="text-secondary">
                Seller:{" "}
                <span className="text-uppercase text-success">
                  {data.created_by}
                </span>
              </h6>
            </div>
          </div>
        </Col>
        <Col className="rounded border bg-light pb-3 pt-3">
          <div className="price">
            <p className="text">Leading offer:</p>
            <p className="cost">{data.original_price} SEK</p>
          </div>
          <div className="date">
            <p className="text">Ends On:</p>
            <p className="end-date">{data.end_date}</p>
          </div>
          <hr />
          <BiddingList data={data} />
          <hr />
          <Form.Group className="d-flex flex-row-reverse">
            <Button disabled={!(isUserOwner || isAdmin)}>End Bid</Button>
          </Form.Group>
        </Col>
      </Row>
    </Container>
  );
}
