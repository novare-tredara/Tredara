// Node modules
import { ChangeEvent } from "react";

// Project files
import Placeholder from "assets/images/placeholders/card-basic.png";
import iInputImage from "interfaces/iInputImage";
import readFile from "scripts/resize-image/readFile";
import resizeImage from "scripts/resize-image/resizeImage";

import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

interface iProps {
  field: iInputImage;
  state: [any, Function];
}

export default function InputImage({ field, state }: iProps) {
  const { key, label, imageWidth } = field;
  const [value, setValue] = state;

  // Properties
  const selectedImage = value[key];
  const imageURL = selectedImage === undefined ? Placeholder : selectedImage;

  // Methods
  async function onChange(event: ChangeEvent<HTMLInputElement>) {
    // Safeguard
    if (!event.currentTarget.files) return;

    const files = event.currentTarget.files;
    const file = files[0];
    const image: string = await readFile(file);
    const resizedImage: Blob = await resizeImage(image, imageWidth, imageWidth);
    const finalImage = await readFile(resizedImage);

    const clonedItem = { ...value };
    clonedItem[key] = finalImage;

    setValue(clonedItem);
  }

  return (
    <Form.Group as={Row} className="mb-3" controlId={key}>
      <Form.Label column sm={2}>
        {label}:
      </Form.Label>
      <Col sm={10}>
        <Form.Control
          type="file"
          accept="image/png, image/jpeg, image/jpg"
          onChange={onChange}
        />
      </Col>
      <img
        src={imageURL}
        alt="img"
        style={{ height: "25rem", padding: "1rem 1rem 0rem 0.7rem" }}
        onError={(event) => (event.currentTarget.src = Placeholder)}
      />
    </Form.Group>
  );
}
