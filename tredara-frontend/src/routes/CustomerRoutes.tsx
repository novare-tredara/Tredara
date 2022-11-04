// Node modules
import { Routes, Route } from "react-router-dom";

// Project files
import Home from "pages/Home";
import NotFound from "pages/NotFound";
import SearchResults from "components/SearchResults";
import NavigationBar from "components/NavigationBar";
import Footer from "components/Footer";

export default function CustomerRoutes() {
  return (
    <div>
      {" "}
      <NavigationBar />
      <Routes>
        <Route path="*" element={<NotFound />} />
        <Route path="/" element={<Home />} />
        <Route path="/getbycategory/accessories/:code" element={<Home />} />
        <Route path="/search" element={<SearchResults />} />
      </Routes>
      <Footer />
    </div>
  );
}
