import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import eStatus from "interfaces/eStatus";
import iAuctionItem from "interfaces/iAuctionItem";
import StatusError from "components/StatusError";
import StatusLoading from "components/StatusLoading";
import Placeholder from "assets/images/placeholders/card.png";

export default function ItemDetail() {
  const [status, setStatus] = useState(eStatus.LOADING);
  const [data, setData] = useState({} as iAuctionItem);
  const { id }: any = useParams();

  useEffect(() => {
    fetch(`http://localhost:8080/api/auctionitems/details/${id}`)
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
    <div id="details">
      <div className="column">
        <img
          src={data.image}
          alt=""
          onError={(event) => (event.currentTarget.src = Placeholder)}
        />
        <div className="detail">
          <h1 className="title">{data.title}</h1>
          <div className="price">
            <p className="text">Leading offer:</p>
            <p className="cost">{data.original_price} SEK</p>
          </div>
          <div className="date">
            <p className="text">Ends on:</p>
            <p className="end-date">{data.end_date}</p>
          </div>
          <div className="history">
            <p className="history-list">Show Bid History</p>
          </div>
          <button className="bid" type="submit">
            Bid
          </button>
        </div>
      </div>
      <div className="desc">
        <p className="desc-heading">Description</p>
        <p className="desc-data">{data.description}</p>
      </div>
    </div>
  );
}
