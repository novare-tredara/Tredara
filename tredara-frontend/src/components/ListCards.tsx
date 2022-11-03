// Project files
import ItemCard from "components/ItemCard";
import iContent from "interfaces/iContent";

interface iProps {
  title: String;
  data: iContent[];
}

export default function ListCards() {
  // Components
  //const Cards = data.map((item) => <ItemCard key={item.id} item={item} />);

  return (
    <section className="card-list">
      <h2>title</h2><ItemCard></ItemCard>
      <div></div>
    </section>
  );
}
