import "bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter } from "react-router-dom";

import CustomerRoutes from "routes/CustomerRoutes";
import UnloggedRoutes from "routes/UnloggedRoutes";
import { useUser } from "state/UserContext";
import eUserType from "interfaces/eUserType";

import "styles/style.css";
import AdminRoutes from "routes/AdminRoutes";

export default function App() {
  const { user } = useUser();

  return (
    <div className="App">
      <BrowserRouter>
        {user === null && <UnloggedRoutes />}
        {user !== null &&
          user?.type !== eUserType.ADMIN &&
          user?.type !== eUserType.CUSTOMER && (
            <>
              {alert("Invalid details")}
              <UnloggedRoutes />
            </>
          )}
        {user?.type === eUserType.ADMIN && <AdminRoutes />}
        {user?.type === eUserType.CUSTOMER && <CustomerRoutes />}
      </BrowserRouter>
    </div>
  );
}
