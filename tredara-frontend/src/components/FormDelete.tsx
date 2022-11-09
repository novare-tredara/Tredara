// MPM packages
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { FormEvent, useState } from "react";
import { Trash } from "react-bootstrap-icons";

// Project files

interface iProps {
  data: any;
}

export default function FormDelete({ data }: iProps) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  // Methods
  async function onSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    fetch("/auctionitems/delete/" + data.id, {
      method: "delete",
    })
      .then(onSuccess)
      .catch((error) => onFailure(error));
  }

  function onSuccess() {
    handleClose();
    alert("Item deleted!");
  }

  function onFailure(error: string) {
    console.error(error);
    alert("Could not delete item");
  }

  return (
    <>
      <Button variant="danger" onClick={handleShow}>
        <Trash /> Delete
      </Button>
      <Modal size="lg" show={show} onHide={handleClose} animation={true}>
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
            Delete Auction Item : {data.title}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={onSubmit}>
            <p>
              Are you sure that you want to delete this item? This action cannot
              be reverted.
            </p>
            <hr />
            <Form.Group className="d-flex flex-row-reverse">
              <Button className="btn btn-danger" onClick={() => handleClose()}>
                Keep
              </Button>
              <Button type="submit">Delete</Button>
            </Form.Group>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}
