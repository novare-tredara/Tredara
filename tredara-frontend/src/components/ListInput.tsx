// Project files
import InputField from "./InputField";
import InputImage from "./InputImage";
import InputSelect from "./InputSelect";

interface iProps {
  fields: any;
  state: [any, Function];
}

export default function ListInput({ fields, state }: iProps) {
  const Inputs = fields.map((item: any, index: number) => {
    switch (item.type) {
      case "select":
        return <InputSelect key={index} field={item} state={state} />;
      case "image":
        return <InputImage key={index} field={item} state={state} />;
      default:
        return <InputField key={index} field={item} state={state} />;
    }
  });

  return Inputs;
}
