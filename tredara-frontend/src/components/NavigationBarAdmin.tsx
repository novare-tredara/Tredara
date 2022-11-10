import { Link, useNavigate } from "react-router-dom";

import Logo from "assets/images/logo-icon.png";
import AdminLinks from "data/links-admin.json";
import NavItem from "./NavItem";
import Search from "./Search";

export default function NavigationBarAdmin() {
  // Global state
  const navigate = useNavigate();

  function onSelect(id: number, endPoint: string) {
    navigate(endPoint);
  }

  function onSubmit() {}

  // Components
  const Links = AdminLinks.map((item) => (
    <NavItem item={item} actions={[onSelect, onSubmit]} />
  ));
  return (
    <nav className="navigation-bar">
      <Link to={AdminLinks[0].url}>
        <img src={Logo} alt="logo" />
      </Link>
      <Search />
      <div className="justify-content-end">{Links}</div>
    </nav>
  );
}
