// Node modules
import { ChangeEvent } from "react";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

// Project files
import iInputField from "interfaces/iInputField";

interface iProps {
  field: iInputField;
  state: [any, Function];
}

export default function InputField({ field, state }: iProps) {
  const { key, autoFocus, label, placeholder, type, required } = field;
  const [value, setValue] = state;

  // Properties
  const initialValue = value[key] ?? "";

  // Methods
  function onChange(event: ChangeEvent<HTMLInputElement>) {
    const newValue = event.target.value;

    changeValue(newValue);
  }

  function changeValue(newValue: any) {
    const clonedItem = { ...value };

    clonedItem[key] = newValue;
    setValue(clonedItem);
  }

  return (
    <Form.Group as={Row} className="mb-3" controlId={key}>
      <Form.Label column sm={2}>
        {label}:
      </Form.Label>
      <Col sm={10}>
        <Form.Control
          autoFocus={autoFocus}
          onChange={onChange}
          placeholder={placeholder}
          required={required}
          type={type}
          value={initialValue}
        />
      </Col>
    </Form.Group>
  );
}
