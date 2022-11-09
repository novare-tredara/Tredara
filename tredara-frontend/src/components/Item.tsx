// Project files
import iAuctionItem from "interfaces/iAuctionItem";
import Placeholder from "assets/images/placeholders/card-basic.png";
import { useState } from "react";
import FormUpdate from "./FormUpdate";
import FormDelete from "./FormDelete";

interface iProps {
  item: iAuctionItem;
}

export default function Item({ item }: iProps) {
  const { id, title, image } = item;

  return (
    <>
      <article className="item-user">
        <span className="number">{id}</span>
        <img
          src={image}
          onError={(event) => (event.currentTarget.src = Placeholder)}
        />
        <h3>{title}</h3>
        <div className="buttons">
          {/* Adding model form for Update */}
          <FormUpdate data={item} />
          <FormDelete data={item} />
        </div>
      </article>
      <hr />
    </>
  );
}