import { Link, useNavigate } from "react-router-dom";

import iNavItem from "interfaces/iNavItem";

interface iProps {
  item: iNavItem;
  actions: Function[];
}

export default function NavItem({ item, actions }: iProps) {
  // Global state
  const [onAction] = actions;

  return (
    <button onClick={() => onAction(item.id, item.url)}>{item.label}</button>
  );
}
