// Node modules
import { FormEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import signup from "../assets/images/signup.png";
import Form from "react-bootstrap/Form";
import { Button, Col, Container, InputGroup, Row } from "react-bootstrap";

// Project files
import ListInput from "components/ListInput";
import Fields from "data/fields-sign-up.json";
import { useUser } from "state/UserContext";
import iUser from "interfaces/iUser";

export default function Login() {
  // Global state
  const navigate = useNavigate();
  const { setUser } = useUser();

  // Local state
  const [form, setForm] = useState({});

  // Properties
  const endPoint = "signup/";

  // Methods
  function onSubmit(event: FormEvent): void {
    event.preventDefault();

    fetch(endPoint, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "x-access-token": "token-value",
      },
      body: JSON.stringify(form),
    })
      .then((response) => response.json())
      .then((result) => onSuccess(result))
      .catch((error) => onFailure(error));
  }

  function onSuccess(newUser: iUser) {
    setUser(newUser);
    navigate("/");
  }

  function onFailure(error: string) {
    console.error(Error);
    alert(`Can't create an account because of ${error}`);
  }

  return (
    <Container id="sign-up" className="auth">
      <Form.Group as={Row} className="mb-3 d-flex login-footer">
        <img className="column" src={signup} />
        <Col sm={6} className="d-flex justify-content-center">
          <Container>
            <Form className="auth-form" onSubmit={(event) => onSubmit(event)}>
              <Container>
                <h3 className="auth-form-title">Registration</h3>
                <Container className="text-center">
                  <ListInput fields={Fields} state={[form, setForm]} />
                  <Container className="d-flex flex-row-reverse">
                    <Button
                      type="submit"
                      className="btn btn-primary d-flex flex-row-reverse"
                    >
                      Create Account
                    </Button>
                    <Form.Label column sm={8}>
                      Already have an account?
                      <Link
                        to="/login"
                        style={{ marginLeft: "0.5rem", color: "#0d6efd" }}
                        className="ml-2 "
                      >
                        Sign In
                      </Link>
                    </Form.Label>
                  </Container>
                </Container>
              </Container>
            </Form>
          </Container>
        </Col>
      </Form.Group>
    </Container>
  );
}
