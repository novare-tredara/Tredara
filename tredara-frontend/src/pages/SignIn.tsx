// Node modules
import { FormEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";

import Logo from "assets/images/logo-icon.png";
import { Key, Person } from "react-bootstrap-icons";

// Project files
import ListInput from "components/ListInput";
import Fields from "data/fields-sign-in.json";
import iUser from "interfaces/iUser";
import { useUser } from "state/UserContext";
import { Button, Col, Container, InputGroup, Row } from "react-bootstrap";

export default function SignIn() {
  // Global state
  const { setUser } = useUser();
  const navigate = useNavigate();

  // Local state
  const [form, setForm] = useState({ email: "", password: "" });

  // Properties
  const endPoint = "login/";

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

  function onSuccess(returningUser: iUser) {
    setUser(returningUser);
    navigate("/");
  }

  function onFailure(error: string) {
    console.error(error);
    alert(error);
  }

  return (
    <Container id="sign-in" className="login">
      <Container className="d-flex justify-content-center h-100">
        <Container className="user_card">
          <Container className="d-flex justify-content-center">
            <Container className="brand_logo_container">
              <img src={Logo} className="brand_logo" alt="Logo" />
            </Container>
          </Container>
          <Container>
            <Form onSubmit={(event) => onSubmit(event)}>
              <h2 style={{ textAlign: "center" }}>Welcome to Tredara</h2>
              <ListInput fields={Fields} state={[form, setForm]} />
              <Form.Group as={Row} className="mb-3 d-flex login-footer">
                <Form.Label column sm={8}>
                  Don't have an account?
                  <Link
                    to="/signup"
                    style={{ marginLeft: "0.5rem", color: "#0d6efd" }}
                    className="ml-2 "
                  >
                    Sign Up
                  </Link>
                </Form.Label>
                <Col sm={4} className="d-flex justify-content-end">
                  <Button type="submit" className="btn btn-primary login_btn">
                    Login
                  </Button>
                </Col>
              </Form.Group>
            </Form>
          </Container>
        </Container>
      </Container>
    </Container>
  );
}
