// Project files
import logo from "assets/images/logo.png";
import background from "assets/images/background.png";

export default function BannerHome() {
  return (
    <header className="hero hero-home">
      <img className="background-image" alt="background" src={background} />
      <div className="content">
        <img src={logo} alt="logo" />
      </div>
    </header>
  );
}
