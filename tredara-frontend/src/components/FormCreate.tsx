import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { FormEvent, useState } from "react";
import Fields from "data/fields-auction-items.json";
import ListInput from "./ListInput";
import { useUser } from "state/UserContext";

interface iProps {
  show: boolean;
  onHide: any;
  actions: Function[];
}
export default function FormCreate(props: iProps) {
  // Local state
  const [form, setForm] = useState({});

  // Global state
  const { user } = useUser();

  const [handleClose] = props.actions;
  const [validated, setValidated] = useState(false);

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      onSubmit(event);
      handleClose();
    }
    setValidated(true);
  }

  // Methods
  async function onSubmit(event: FormEvent<HTMLFormElement>) {
    const newItem = { ...form, user_email: user?.email };
    event.preventDefault();
    fetch("/auctionitems/create/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      body: JSON.stringify(newItem),
    })
      .then(onSuccess)
      .catch((error) => onFailure(error));
    console.log(newItem);
  }

  function onSuccess() {
    alert("Item created!");
  }

  function onFailure(error: string) {
    console.error(error);
    alert("Could not create item");
  }

  return (
    <Modal size="lg" show={props.show} onHide={props.onHide} animation={true}>
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Create Auction Item
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <ListInput fields={Fields} state={[form, setForm]} />
          <Form.Group className="d-flex flex-row-reverse">
            <Button className="btn btn-danger" onClick={() => handleClose()}>
              Close
            </Button>
            <Button className="btn btn-success" type="submit">
              Save
            </Button>
          </Form.Group>
        </Form>
      </Modal.Body>
    </Modal>
  );
}
