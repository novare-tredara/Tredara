import "bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter } from "react-router-dom";

import CustomerRoutes from "routes/CustomerRoutes";

import "styles/style.css";

export default function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <CustomerRoutes />
      </BrowserRouter>
    </div>
  );
}
