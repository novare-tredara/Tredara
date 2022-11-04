// Project files
import Placeholder from "assets/images/placeholders/card.png";
//import ModalDetails from "components/ModalDetails";
import iAuctionItem from "interfaces/iAuctionItem";
import { useModal } from "state/ModalContext";
import Card from "react-bootstrap/Card";
import Col from "react-bootstrap/Col";

interface iProps {
  item: iAuctionItem;
}

export default function ItemCard({ item }: iProps) {
  const { image } = item;

  // Global state
  const { setModal } = useModal();

  // Components
  //const Modal = <ModalDetails item={item} />;

  return (
    <article /*onClick={() => setModal(Modal)}*/ className="card-list col-sm-2">
      <Card key={item.id}>
        <Card.Img
          id={item.title}
          className="card-img-alt"
          src={item.image}
          onError={(event) => (event.currentTarget.src = Placeholder)}
        />
        <Card.Body key={item.id}>
          <Card.Title id={item.title}>{item.title}</Card.Title>
        </Card.Body>
      </Card>
    </article>
  );
}
