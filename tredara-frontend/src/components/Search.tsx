import search from "../assets/images/icons/search.svg";
import { useState } from "react";

import { useNavigate } from "react-router-dom";
export default function Search() {
  const [searchWord, setSearchWord] = useState("");

  const navigate = useNavigate();

  function onSubmit(event: any) {
    event.preventDefault();
    navigate(`/search/${searchWord}`);
  }

  return (
    <div>
      <form className="search" onSubmit={onSubmit}>
        <input
          type="text"
          placeholder="search"
          value={searchWord}
          onChange={(event) => setSearchWord(event.target.value)}
        />

        {/*         <img src={search} />
         */}
      </form>
    </div>
  );
}
