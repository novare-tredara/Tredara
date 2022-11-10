export default interface iAuctionItem {
  id: number;
  title: string;
  description: string;
  image: string;
  original_price: number;
  sold_price: number;
  start_date: string;
  end_date: string;
  status: number;
  created_by: string;
  user_email: string;
}
