import { Routes, Route } from "react-router-dom";

// Project files
import SignIn from "pages/SignIn";
import SignUp from "pages/SignUp";

export default function UnloggedRoutes() {
  return (
    <Routes>
      <Route path="*" element={<SignIn />} />
      <Route path="/" element={<SignIn />} />
      <Route path="/login" element={<SignIn />} />
      <Route path="/signup" element={<SignUp />} />
    </Routes>
  );
}