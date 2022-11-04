import { Link, useNavigate } from "react-router-dom";

import Logo from "assets/images/logo-icon.png";
import CustomerLinks from "data/links-customer.json";
import { useUser } from "state/UserContext";
import { useState } from "react";

export default function NavigationBar() {
  // Global state
  const { setUser } = useUser();
  const[searchWord, setSearchWord] = useState("");
  
  const navigate = useNavigate();
  
  function onSubmit(event:any){
    event.preventDefault();
    navigate(`/search/${searchWord}`)
}

  // Components
  const Links = CustomerLinks.map((item) => (
    <Link key={item.id} to={item.url}>
      {item.label}
    </Link>
  ));
  return (
    <nav className="navigation-bar">
      <Link to={CustomerLinks[0].url}>
        <img src={Logo} />
      </Link>
      <div className="right-items">
      <form className="search" onSubmit={onSubmit}>
        <input
          id="search"
          className="form-control full-width form-rounded"
          type="search"
          placeholder="Search..."
          aria-label="Search"
          onChange={(event) => setSearchWord(event.target.value)}
        />
        </form>
      </div>
      <div className="justify-content-end">{Links}</div>
    </nav>
  );
}
