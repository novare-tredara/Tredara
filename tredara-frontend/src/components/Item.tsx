// Project files
import iAuctionItem from "interfaces/iAuctionItem";
import Placeholder from "assets/images/placeholders/card-basic.png";
import FormUpdate from "./FormUpdate";
import FormDelete from "./FormDelete";
import { Link } from "react-router-dom";
import { useUser } from "state/UserContext";
import eUserType from "interfaces/eUserType";

interface iProps {
  item: iAuctionItem;
  actions: Function[];
}

export default function Item({ item, actions }: iProps) {
  const { id, title, image } = item;
  const [onReload] = actions;
  const { user } = useUser();
  const isAdmin = user?.type === eUserType.ADMIN;

  return (
    <>
      <article className="item-user">
        <span className="number">{id}</span>
        <Link to={`/detail/${item.id}`} className="link">
          <img
            src={image}
            alt="img"
            onError={(event) => (event.currentTarget.src = Placeholder)}
          />
        </Link>
        <h3>{title}</h3>
        <div className="buttons">
          {/* Adding model form for Update */}
          {isAdmin ? "" : <FormUpdate data={item} actions={[onReload]} />}
          <FormDelete data={item} actions={[onReload]} />
        </div>
      </article>
      <hr />
    </>
  );
}
