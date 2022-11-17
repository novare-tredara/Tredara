import { Link, useNavigate } from "react-router-dom";

import Logo from "assets/images/logo-icon.png";
import CustomerLinks from "data/links-customer.json";
import { useUser } from "state/UserContext";
import { useState } from "react";
import NavItem from "./NavItem";
import Search from "./Search";
import FormCreate from "./FormCreate";

export default function NavigationBar() {
  // Global state
  const { user, setUser } = useUser();
  const navigate = useNavigate();
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  function onSelect(id: number, endPoint: string) {
    if (id === 1) {
      handleShow();
    } else {
      navigate(endPoint);
    }
  }

  function onSubmit() {}

  function onLogout() {
    setUser(null);
    navigate("/");
  }

  // Components
  const Links = CustomerLinks.map((item) => (
    <NavItem item={item} actions={[onSelect, onSubmit]} />
  ));
  return (
    <nav className="navigation-bar">
      <Link to={CustomerLinks[0].url}>
        <img src={Logo} alt="logo" />
      </Link>
      <button>Welcome {user?.name}</button>
      <Search />
      <div className="justify-content-end">
        {Links}
        <button onClick={onLogout}>Logout</button>
      </div>
      {/* Adding model form for creation */}
      <FormCreate show={show} onHide={handleClose} actions={[handleClose]} />
    </nav>
  );
}
