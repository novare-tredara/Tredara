// Project files
import ItemCard from "components/ItemCard";
import iAuctionItem from "interfaces/iAuctionItem";

interface iProps {
  title: String;
  data: iAuctionItem[];
}

export default function ListCards({ title, data }: iProps) {
  // Components
  const Cards = data.map((item) => <ItemCard key={item.id} item={item} />);
  return (
    <section className="card-list">
      <h2>{title}</h2>
      <div>{Cards}</div>
    </section>
  );
}
