import iInputField from "interfaces/iInputField";
import iInputImage from "interfaces/iInputImage";
import iInputSelect from "interfaces/iInputSelect";

interface DynamicObject {
  [key: string]: string | number | boolean;
}

export function generateFields(
  fields: Array<iInputField | iInputImage | iInputSelect>,
  data: any
) {
  let result: DynamicObject = {};

  fields.forEach((item) => {
    const key: string = item.key;

    result[key] = data[key];
  });

  return result;
}
