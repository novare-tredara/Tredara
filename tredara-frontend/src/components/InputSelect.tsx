// Node modules
import { ChangeEvent, useEffect } from "react";

// Project files
import iInputSelect from "interfaces/iInputSelect";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

interface iProps {
  field: iInputSelect;
  state: [any, Function];
}

export default function Select({ field, state }: iProps) {
  const { label, key, options } = field;
  const [value, setValue] = state;

  function onChange(event: ChangeEvent<HTMLSelectElement>) {
    const userSelectedValue = Number(event.target.value);

    changeValue(userSelectedValue);
  }

  function changeValue(newValue: number) {
    const clonedItem = { ...value };

    clonedItem[key] = newValue;
    setValue(clonedItem);
  }

  // Components
  const Options = options.map((item: string, index: number) => (
    <option key={index} value={index + 1}>
      {item}
    </option>
  ));

  return (
    <Form.Group as={Row} className="mb-3" controlId={key}>
      <Form.Label column sm={2}>
        {label}:
      </Form.Label>
      <Col sm={10}>
        <Form.Select value={value[key]} onChange={onChange}>
          <option selected disabled>
            Please choose an option
          </option>
          {Options}
        </Form.Select>
      </Col>
    </Form.Group>
  );
}
