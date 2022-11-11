// Node modules
import { useEffect, useState } from "react";

// Project files
import BannerHome from "components/HeroHome";
import ContainerCards from "components/ListCards";
import StatusEmpty from "components/StatusEmpty";
import StatusError from "components/StatusError";
import StatusLoading from "components/StatusLoading";
import eStatus from "interfaces/eStatus";
import iAuctionItem from "interfaces/iAuctionItem";

export default function Home() {
  // Local state
  const [status, setStatus] = useState(eStatus.LOADING);
  const [data, setData] = useState(new Array<iAuctionItem>());

  // Properties
  const endPoint = "auctionitems/getbystatus/1";

  // Methods
  useEffect(() => {
    fetch(endPoint)
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

  // Safeguards
  if (status === eStatus.LOADING) return <StatusLoading />;
  if (status === eStatus.ERROR) return <StatusError />;
  if (data.length === 0) return <StatusEmpty />;

  return (
    <div id="home">
      <BannerHome />
      <ContainerCards title="Auction Items" data={data} />
    </div>
  );
}
