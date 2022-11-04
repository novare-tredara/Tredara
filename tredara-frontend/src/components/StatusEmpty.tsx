// Project files
import Icon from "assets/images/icons/icon-no-movie-red.svg";

export default function StatusEmpty() {
  return (
    <div className="status">
      <div className="content">
        <img src={Icon} />
        <p>We are working on adding content please come back soon!</p>
      </div>
    </div>
  );
}
