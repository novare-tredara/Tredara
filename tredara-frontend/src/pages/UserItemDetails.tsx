// Node modules
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

// Project files
import NavigationBar from "components/NavigationBar";
import StatusEmpty from "components/StatusEmpty";
import StatusError from "components/StatusError";
import StatusLoading from "components/StatusLoading";
import eStatus from "interfaces/eStatus";
import iAuctionItem from "interfaces/iAuctionItem";
import Item from "../components/Item";
import { useUser } from "state/UserContext";

export default function UserItems() {
  // Global state
  const { user } = useUser();
  console.log(user);
  // Local state
  const [status, setStatus] = useState(eStatus.LOADING);
  const [data, setData] = useState(new Array<iAuctionItem>());

  const code = "radha@tredara.com"; //user?.email;
  const endPoint = "/auctionitems/getitemsbyuser/";
  // Methods
  useEffect(() => {
    fetch(endPoint + code + "/")
      .then((response) => response.json())
      .then((result) => onSuccess(result))
      .catch((error) => onFailure(error));
  }, []);

  function onSuccess(data: iAuctionItem[]) {
    setData(data);
    setStatus(eStatus.READY);
  }

  function onFailure(error: string) {
    console.error(error);
    setStatus(eStatus.ERROR);
  }

  // Components
  const Items = data.map((item) => <Item key={item.id} item={item} />);

  // Safeguards
  if (status === eStatus.LOADING) return <StatusLoading />;
  if (status === eStatus.ERROR) return <StatusError />;

  return (
    <div id="user-items" className="user-list-page">
      <NavigationBar />
      <header>
        <h1>User Auction Items</h1>
      </header>
      {data.length === 0 ? <StatusEmpty /> : Items}
    </div>
  );
}
