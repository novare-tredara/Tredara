// Node modules
import { createContext, ReactNode, useContext, useState } from "react";

// Project files
import iUser from "interfaces/iUser";

// Interfaces
interface iProps {
  children: ReactNode;
}
interface iValues {
  user: iUser | null;
  setUser: Function;
}

// Properties
const initialValues: iValues = {
  user: null,
  setUser: () => {},
};
const Context = createContext(initialValues);

// Methods
export function UserProvider({ children }: iProps) {
  // Local state
  const [user, setUser] = useState(null);

  // Properties
  const value: iValues = { user, setUser };

  return <Context.Provider value={value}>{children}</Context.Provider>;
}

export function useUser() {
  const context = useContext(Context);
  const errorText =
    "To use useUser(), you need to wrap the parent component with <UserProvider/>";

  // Safeguards
  if (!context) throw new Error(errorText);

  return context;
}
