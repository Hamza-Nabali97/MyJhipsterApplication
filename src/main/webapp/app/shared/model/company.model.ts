import { ITile } from 'app/shared/model//tile.model';

export interface ICompany {
    id?: number;
    name?: string;
    location?: string;
    tiles?: ITile[];
}

export class Company implements ICompany {
    constructor(public id?: number, public name?: string, public location?: string, public tiles?: ITile[]) {}
}
