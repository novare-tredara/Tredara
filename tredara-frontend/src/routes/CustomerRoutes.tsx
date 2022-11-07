// Node modules
import { Routes, Route } from "react-router-dom";

// Project files
import Home from "pages/Home";
import NotFound from "pages/NotFound";
import SearchResults from "components/SearchResults";
import NavigationBar from "components/NavigationBar";
import Footer from "components/Footer";
import SignIn from "pages/SignIn";
import SignUp from "pages/SignUp";

export default function CustomerRoutes() {
  return (
    <div>
      {" "}
      <NavigationBar />
      <Routes>
        <Route path="*" element={<SignIn />} />
        <Route path="/" element={<Home />} />
        <Route path ="/signup" element={<SignUp/>}/>
        <Route path="/getbycategory/accessories/:code" element={<Home />} />
        <Route path="/search/:query" element={<SearchResults />} />
      </Routes>
      <Footer />
    </div>
  );
}
