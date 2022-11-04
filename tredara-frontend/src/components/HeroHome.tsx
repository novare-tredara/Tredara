// Project files
import IconInfo from "assets/images/icons/icon-info-white.svg";
import Placeholder from "assets/images/placeholders/banner.png";
import logo from "assets/images/logo.png";
import background from "assets/images/background.png";
//import ModalDetails from "components/ModalDetails";
import iAuctionItem from "interfaces/iAuctionItem";
import { useModal } from "state/ModalContext";

interface iProps {
  item: iAuctionItem;
}

export default function BannerHome({ item }: iProps) {
  const { image, title, description } = item;

  // Global state
  const { setModal } = useModal();

  // Components
  const Heading = <h3>{title}</h3>;
  //const Modal = <ModalDetails item={item} />;

  return (
    <header className="hero hero-home">
      <img
        className="background-image"
        src={background}
        onError={(event) => (event.currentTarget.src = Placeholder)}
      />
      <div className="content">
        <img className="background-image" src={logo} />
      </div>
    </header>
  );
}
