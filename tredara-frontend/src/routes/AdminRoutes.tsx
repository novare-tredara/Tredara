// Node modules
import { Routes, Route } from "react-router-dom";

// Project files
import Home from "pages/Home";
import NotFound from "pages/NotFound";
import SearchResults from "pages/SearchResults";
import Footer from "components/Footer";
import ItemDetail from "pages/ItemDetail";
import UserItems from "pages/UserItemDetails";
import NavigationBarAdmin from "components/NavigationBarAdmin";
import UnloggedRoutes from "./UnloggedRoutes";

export default function AdminRoutes() {
  return (
    <div>
      <NavigationBarAdmin />
      <Routes>
        <Route path="*" element={<NotFound />} />
        <Route path="/" element={<Home />} />
        <Route path="/detail/:id" element={<ItemDetail />} />
        <Route path="/user-items" element={<UserItems />} />
        <Route path="/search/:query" element={<SearchResults />} />
        <Route path="/login" element={<UnloggedRoutes />} />
      </Routes>
      <Footer />
    </div>
  );
}
