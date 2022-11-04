// Project files
import ItemCard from "components/ItemCard";
import iAuctionItem from "interfaces/iAuctionItem";
import Row from "react-bootstrap/Row";

interface iProps {
  title: String;
  data: iAuctionItem[];
}

export default function ListCards({ title, data }: iProps) {
  // Components
  const Cards = data.map((item) => <ItemCard key={item.id} item={item} />);
  return (
    <section className="row">
      <h2>{title}</h2>
      {Cards}
    </section>
  );
}
