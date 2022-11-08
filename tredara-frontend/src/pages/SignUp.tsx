// Node modules
import { FormEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import signup from "../assets/images/signup.png";

// Project files
import ListInput from "components/ListInput";
import Fields from "data/fields-sign-up.json";
import { useUser } from "state/UserContext";
import iUser from "interfaces/iUser";

export default function Login() {
  // Global state
  const navigate = useNavigate();
  const { user, setUser } = useUser();

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
      body: JSON.stringify(form)
    })
      .then((response) => response.json())
      .then((result) => onSuccess(result))
      .catch((error) => onFailure(error));
  }

  function onSuccess(newUser: iUser) {
   alert("Welcome to Tradera!");
    setUser(newUser);
    navigate("/");
  }

  function onFailure(error: string) {
    console.error(Error);
    alert(`Can't create an account because of ${error}`);
  }

  return (
    <div id="sign-up" className="auth">
      <div className="container">
        <div className="row">
        <img className ="column" src ={signup}/>
        <div className ="column">
        <h1>Sign Up</h1>
        <form onSubmit={(event) => onSubmit(event)}>
          <ListInput fields={Fields} state={[form, setForm]} />
          <button>Create Account</button>
        </form>
        <footer>
          <p>
            Already have an account? <Link to="/login">Sign In</Link>.
          </p>
        </footer>
        </div>
        </div>
      </div>
    </div>
  );
}