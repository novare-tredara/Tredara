import iNavItem from "interfaces/iNavItem";

interface iProps {
  item: iNavItem;
  actions: Function[];
}

export default function NavItem({ item, actions }: iProps) {
  // Global state
  const [onAction] = actions;

  return (
    <button
      id={String(item.id)}
      key={item.id}
      onClick={() => onAction(item.id, item.url)}
    >
      {item.label}
    </button>
  );
}
