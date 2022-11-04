// Project files
import Icon from "assets/images/icons/icon-error-red.svg";

export default function StatusError() {
  return (
    <div className="status">
      <div className="content">
        <img src={Icon} />
        <p>Could not load data please reload the page and try again</p>
      </div>
    </div>
  );
}
