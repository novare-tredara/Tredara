// Node modules
import { Routes, Route } from "react-router-dom";

// Project files
import Home from "pages/Home";
import NotFound from "pages/NotFound";
import Content from "pages/Content";
import SearchResults from "pages/SearchResults";
import NavigationBar from "components/NavigationBar";
import Footer from "components/Footer";
import ItemDetail from "pages/ItemDetail";

export default function CustomerRoutes() {
  return (
    <div>
      <NavigationBar />
      <Routes>
        <Route path="*" element={<NotFound />} />
        <Route path="/" element={<Home />} />
        <Route path="/:code" element={<Content />} />
        <Route path="/search/:query" element={<SearchResults />} />
        <Route path="/detail/:id" element={<ItemDetail />} />
      </Routes>
      <Footer />
    </div>
  );
}
