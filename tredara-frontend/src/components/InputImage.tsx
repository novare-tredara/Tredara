// Node modules
import { ChangeEvent } from "react";

// Project files
import Placeholder from "assets/images/placeholders/card-basic.png";
import iInputImage from "interfaces/iInputImage";
import readFile from "scripts/resize-image/readFile";
import resizeImage from "scripts/resize-image/resizeImage";

interface iProps {
  field: iInputImage;
  state: [any, Function];
}

export default function InputImage({ field, state }: iProps) {
  const { key, label, imageWidth } = field;
  const [value, setValue] = state;

  // Properties
  const selectedImage = value[key];
  const imageURL = selectedImage === undefined ? Placeholder : selectedImage;

  // Methods
  async function onChange(event: ChangeEvent<HTMLInputElement>) {
    // Safeguard
    if (!event.currentTarget.files) return;

    const files = event.currentTarget.files;
    const file = files[0];
    const image: string = await readFile(file);
    const resizedImage: Blob = await resizeImage(image, imageWidth, 0);
    const finalImage = await readFile(resizedImage);

    const clonedItem = { ...value };
    clonedItem[key] = finalImage;

    setValue(clonedItem);
  }

  return (
    <label className="input input-image">
      <span>{label}:</span>
      <input
        type="file"
        accept="image/png, image/jpeg"
        onChange={(event) => onChange(event)}
      />
      <img
        src={imageURL}
        onError={(event) => (event.currentTarget.src = Placeholder)}
      />
    </label>
  );
}
