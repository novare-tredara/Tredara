// Node modules
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

// Project files
import NavigationBar from "components/NavigationBar";
import NavigationBarAdmin from "components/NavigationBarAdmin";
import StatusEmpty from "components/StatusEmpty";
import StatusError from "components/StatusError";
import StatusLoading from "components/StatusLoading";
import eStatus from "interfaces/eStatus";
import iAuctionItem from "interfaces/iAuctionItem";
import Item from "../components/Item";
import { useUser } from "state/UserContext";
import eUserType from "interfaces/eUserType";

export default function UserItems() {
  // Global state
  const { user } = useUser();
  // Local state
  const [status, setStatus] = useState(eStatus.LOADING);
  const [data, setData] = useState(new Array<iAuctionItem>());

  const activeItems = data.filter((item) => item.status === 1);
  const inActiveItems = data.filter((item) => item.status === 2);

  const code = user?.email;
  const isAdmin = user?.type === eUserType.ADMIN;

  const endPoint = isAdmin
    ? "/auctionitems"
    : "/auctionitems/getitemsbyuser/" + code;
  // Methods
  useEffect(() => {
    fetch(endPoint + "/")
      .then((response) => response.json())
      .then((result) => onSuccess(result))
      .catch((error) => onFailure(error));
  }, [status]);

  function onSuccess(data: iAuctionItem[]) {
    setData(data);
    setStatus(eStatus.READY);
  }

  function onFailure(error: string) {
    console.error(error);
    setStatus(eStatus.ERROR);
  }
  function onReload() {
    setStatus(eStatus.LOADING);
  }

  // Components
  const ActiveItems = activeItems.map((item) => (
    <Item key={item.id} item={item} actions={[onReload]} />
  ));

  const InActiveItems = inActiveItems.map((item) => (
    <Item key={item.id} item={item} actions={[onReload]} />
  ));

  // Safeguards
  if (status === eStatus.LOADING) return <StatusLoading />;
  if (status === eStatus.ERROR) return <StatusError />;

  return (
    <div id="user-items" className="user-list-page">
      {isAdmin ? <NavigationBarAdmin /> : <NavigationBar />}
      <header>
        <h1>Active Auction Items</h1>
      </header>
      {data.length === 0 ? <StatusEmpty /> : ActiveItems}
      <header>
        <h1>Inactive Auction Items</h1>
      </header>
      {data.length === 0 ? <StatusEmpty /> : InActiveItems}
    </div>
  );
}
