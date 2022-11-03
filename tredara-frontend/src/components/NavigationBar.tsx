import { Link } from "react-router-dom";
import Logo from "assets/images/logo-icon.png";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";

export default function NavigationBar() {
  return (
    <nav className="navigation-bar">
      <img src={Logo} />
      <div className="d-flex justify-content-start">
        <div className="col-md-5">
          <input
            id="search"
            className="form-control full-width form-rounded"
            type="search"
            placeholder="Search..."
            aria-label="Search"
          />
        </div>
      </div>

      <Link to="/NewListing">NewListing</Link>
      <Link to="/Accessories">Accessories</Link>
      <Link to="/BeautyCare">BeautyCare</Link>
      <Link to="/Books">Books</Link>
      <Link to="/Mobiles">Mobiles</Link>
      <Link to="/Clothes">Clothes</Link>
      <Link to="/Login">Login</Link>
      <Link to="/Signup">Signup</Link>
    </nav>
  );
}
