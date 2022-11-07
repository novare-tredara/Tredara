import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import eStatus from "interfaces/eStatus";
import iContent from "interfaces/iAuctionItem";
import ContainerCards from "components/ListCards";
import iAuctionItem from "interfaces/iAuctionItem";
import StatusError from "components/StatusError";
import StatusLoading from "components/StatusLoading";

export default function SearchResults() {
  const [status, setStatus] = useState(eStatus.LOADING);
  const [data, setData] = useState(new Array<iContent>());
  const { query }: any = useParams();

  useEffect(() => {
    fetch(`/auctionitems/search?freeText=${query}`)
      .then((response) => response.json())
      .then((data) => onSuccess(data))
      .catch((error) => onFailure(error));
  }, [query]);

  function onSuccess(data: iAuctionItem[]) {
    setData(data);
    setStatus(eStatus.READY);
  }

  function onFailure(error: string) {
    console.error(error);
    setStatus(eStatus.ERROR);
  }

  const results = data.filter((item) =>
    item.title.toLowerCase().match(query.toLowerCase())
  );

  // Safeguards
  if (status === eStatus.LOADING) return <StatusLoading />;
  if (status === eStatus.ERROR) return <StatusError />;

  return (
    <div id="content">
      {results.length === 0 ? (
        <p className="text">No results found for {query}</p>
      ) : (
        <div>
          <header>
            <h1>Items releated to {query}</h1>
          </header>
          <ContainerCards title="Titles avaialble" data={data} />
        </div>
      )}
    </div>
  );
}
