enum Size {
    SMALL, MEDIUM, LARGE, XL,
}

export interface Jersey {
    id: number,
    name: string,
    cost: number,
    size: Size,
    isHome: boolean,
    number: number,
}