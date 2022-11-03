import { ChangeEvent, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import eStatus from "interfaces/eStatus";
import iContent from "interfaces/iContent";
import eCategory from "interfaces/eCategory";
import ContainerCards from "components/ListCards";

export default function SearchResults(){
const [status, setStatus] = useState(eStatus.LOADING);
  const [data, setData] = useState(new Array<iContent>());
  const {query}:any = useParams();
  const endPoint ="/api/content";
    useEffect(() => {
        fetch(`/api/content`)
          .then((response) => response.json())
          .then((data)=>onSuccess(data))
          .catch((error) => onFailure(error));
      }, []);
      function onSuccess(data: iContent[]) {
        setData(data);
       setStatus(eStatus.READY);
      }
    
    
      function onFailure(error: string) {
        console.error(error);
        setStatus(eStatus.ERROR);
      }

      const results = data.filter((item)=>item.title.toLowerCase().match(query.toLowerCase()));
    
    return( 
    <div id="content"><h2> Search Results</h2><ContainerCards></ContainerCards>
        {/* <div >{
            results.length ===0?(<p>No results found for {query}</p>):(<ContainerCards title="Titles avaialble" data={results} />)
        }</div> */}
    </div>
    )
}