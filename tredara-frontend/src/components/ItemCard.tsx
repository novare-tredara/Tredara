// Project files
import { Link } from "react-router-dom";
import Placeholder from "assets/images/placeholders/card.png";
import iAuctionItem from "interfaces/iAuctionItem";

interface iProps {
  item: iAuctionItem;
}

export default function ItemCard({ item }: iProps) {
  return (
    <article className="item-card">
      <Link to={`/detail/${item.id}`} className="link">
        <img
          src={item.image}
          alt=""
          onError={(event) => (event.currentTarget.src = Placeholder)}
        />
        <h3>{item.title}</h3>
        <div className="data">
          <p className="price">{item.original_price} SEK</p>
          <p>{item.end_date}</p>
        </div>
      </Link>
    </article>
  );
}
