import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Footer from "./components/Footer";
import NavigationBar from "./components/NavigationBar";
import SearchResults from "./components/SearchResults";
import Home from "./pages/Home";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import "./styles/style.css";

export default function App() {
  return (
    <div className="App">
      <Router>
        <NavigationBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/search/:query" element={<SearchResults/>}/>
          <Route path="/login" element={<SignIn />} />
          <Route path="/register" element={<SignUp />} />
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}
