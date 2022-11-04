// Project files
import Placeholder from "assets/images/placeholders/card.png";
import iAuctionItem from "interfaces/iAuctionItem";

interface iProps {
  item: iAuctionItem;
}

export default function ItemCard({ item }: iProps) {
  return (
    <article className="item-card">
      <img
        src={item.image}
        alt=""
        onError={(event) => (event.currentTarget.src = Placeholder)}
      />
      <h3>{item.title}</h3>
    </article>
  );
}
