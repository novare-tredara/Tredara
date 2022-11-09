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
import UserItems from "pages/UserItemDetails";

export default function CustomerRoutes() {
  return (
    <div>
      <NavigationBar />
      <Routes>
        <Route path="*" element={<NotFound />} />
        <Route path="/" element={<Home />} />
        <Route path="/detail/:id" element={<ItemDetail />} />
        <Route path="/user-items" element={<UserItems />} />
        <Route path="/search/:query" element={<SearchResults />} />
        <Route path="/:code" element={<Content />} />
      </Routes>
      <Footer />
    </div>
  );
}
