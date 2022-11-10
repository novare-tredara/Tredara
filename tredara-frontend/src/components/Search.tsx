import { useState } from "react";

import { useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import { Search as Find } from "react-bootstrap-icons";
import InputGroup from "react-bootstrap/InputGroup";
import Button from "react-bootstrap/Button";

export default function Search() {
  const [searchWord, setSearchWord] = useState("");

  const navigate = useNavigate();

  function onSubmit(event: any) {
    event.preventDefault();
    navigate(`/search/${searchWord}`);
  }

  return (
    <div className="col-md-4">
      <Form onSubmit={onSubmit}>
        <InputGroup className="mr-sm-2">
          <Form.Control
            onChange={(event) => setSearchWord(event.target.value)}
            placeholder="Search"
            type="search"
            value={searchWord}
          />
          <Button
            className="btn btn-success"
            variant="primary"
            id="button-addon2"
          >
            <Find />
          </Button>
        </InputGroup>

        {/*         <img src={search} />
         */}
      </Form>
    </div>
  );
}
