// Node modules
import { Routes, Route } from "react-router-dom";

// Project files
import Home from "pages/Home";
import NotFound from "pages/NotFound";
import Content from "pages/Content";
import SearchResults from "pages/SearchResults";
import NavigationBar from "components/NavigationBar";
import NavigationBarAdmin from "components/NavigationBarAdmin";

import Footer from "components/Footer";
import ItemDetail from "pages/ItemDetail";
import UserItems from "pages/UserItemDetails";
import { useUser } from "state/UserContext";
import eUserType from "interfaces/eUserType";

export default function CustomerRoutes() {
  const { user } = useUser();
  const isAdmin = user?.type === eUserType.ADMIN;
  return (
    <div>
      {isAdmin ? <NavigationBarAdmin /> : <NavigationBar />}
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
