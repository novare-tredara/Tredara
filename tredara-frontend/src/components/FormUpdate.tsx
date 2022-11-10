import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { FormEvent, useState } from "react";
import Fields from "data/fields-auction-items.json";
import ListInput from "./ListInput";
import { generateFields } from "scripts/formUtilities";
import { PencilSquare } from "react-bootstrap-icons";
import { userInfo } from "os";
import { useUser } from "state/UserContext";

interface iProps {
  data: any;
}
export default function FormUpdate({ data }: iProps) {
  // Local state
  const [form, setForm] = useState(generateFields(Fields, data));
  const { user } = useUser();

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

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
    event.preventDefault();
    const editedItem = {
      ...form,
      id: data.id,
      start_date: data.start_date,
      user: user?.email,
    };
    fetch("/auctionitems/update/", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      body: JSON.stringify(editedItem),
    })
      .then(onSuccess)
      .catch((error) => onFailure(error));
  }

  function onSuccess() {
    alert("Item updated!");
  }

  function onFailure(error: string) {
    console.error(error);
    alert("Could not update item");
  }

  return (
    <>
      <Button onClick={handleShow}>
        <PencilSquare /> Update
      </Button>

      <Modal size="lg" show={show} onHide={handleClose} animation={true}>
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
            Update Auction Item
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <ListInput fields={Fields} state={[form, setForm]} />
            <Form.Group className="d-flex flex-row-reverse">
              <Button className="btn btn-danger" onClick={() => handleClose()}>
                Close
              </Button>
              <Button type="submit">Update</Button>
            </Form.Group>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}
