export interface ITile {
    id?: number;
    name?: string;
    code?: string;
    size?: string;
    placement?: string;
    polishType?: string;
    costPrice?: number;
    minimumSellingPrice?: number;
    inventoryQunatity?: number;
    companyId?: number;
}

export class Tile implements ITile {
    constructor(
        public id?: number,
        public name?: string,
        public code?: string,
        public size?: string,
        public placement?: string,
        public polishType?: string,
        public costPrice?: number,
        public minimumSellingPrice?: number,
        public inventoryQunatity?: number,
        public companyId?: number
    ) {}
}
