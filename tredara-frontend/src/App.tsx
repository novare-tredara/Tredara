import "bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter } from "react-router-dom";

import CustomerRoutes from "routes/CustomerRoutes";
import UnloggedRoutes from "routes/UnloggedRoutes";
import { useUser } from "state/UserContext";
import eUserType from "interfaces/eUserType";

import "styles/style.css";

export default function App() {
  const { user } = useUser();

  return (
    <div className="App">
      <BrowserRouter>
        {user === null && (
          <>
            {alert("Invalid details")}
            <UnloggedRoutes />
          </>
        )}
        <CustomerRoutes />
      </BrowserRouter>
    </div>
  );
}
