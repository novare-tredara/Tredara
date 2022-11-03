import Search from "./Search";

export default function NavigationBar() {
  return (
    <nav className="navigation-bar">
      <h1>Nav Bar</h1>
      <div className="left-items">
        {/* Search bar goes here... */}
        <Search/></div>
    </nav>
  );
}
